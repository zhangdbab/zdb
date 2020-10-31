package demo.zookeeper.synthesizeroperate;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by DJ009828 on 2020/10/31 11:05
 * 分布式锁
 */
public class DistributedLock  implements Lock , Watcher {

    private ZooKeeper zk = null ;
    private String ROOT_LOCK="/locks"; //定义根节点
    private String WAIT_LOCK;//等待前一个锁
    private String CURRENT_LOCK; //表示当前的锁
    private CountDownLatch countDownLatch;


    public DistributedLock() throws IOException, KeeperException, InterruptedException {
        //链接zk
        zk= new ZooKeeper("10.7.90.131:2181,10.7.90.130:2181,10.7.90.199:2181",400,
                this);
        Thread.sleep(3000);
        Stat stat = zk.exists(ROOT_LOCK,false);
        if(stat==null){
            //创建根节点
            zk.create(ROOT_LOCK,"0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }

    }

    @Override
    public void lock() {
        if(this.tryLock()){
            System.out.println(Thread.currentThread().getName()+""+CURRENT_LOCK+"->"+"获得锁成功");
            return;
        }
        try {
            waitForLock(WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //持续阻塞获取锁
    private  boolean waitForLock(String prev ) throws KeeperException, InterruptedException {
        //监听当前节点的上一个节点
        Stat stat = zk.exists(prev,true);
        if(stat!=null){
        System.out.println(Thread.currentThread().getName()+"->等待锁"+ROOT_LOCK+"/"+prev+"释放");
        countDownLatch =new CountDownLatch(1);
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"->获得锁成功");

        }
        return  true;

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            //创建临时有序节点
            CURRENT_LOCK=zk.create(ROOT_LOCK+"/","0".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName()+"->"+CURRENT_LOCK+"尝试竞争");
            //获取子节点
            List<String> childs= zk.getChildren(ROOT_LOCK,false);
            //定义集合
            SortedSet<String>  sortedSet =new TreeSet<>();
            //路径添加到集合当中
            for (String  child:childs){
                sortedSet.add(ROOT_LOCK+"/"+child);
            }
            //获得最小节点
            String firstNode =sortedSet.first();
            //返回小于 CURRENT_LOCK集合
            SortedSet<String> lessThenMe=((TreeSet<String>) sortedSet).headSet(CURRENT_LOCK);
            //通过当前中锁的节点最小的节点进行必较，如果相等表示获得锁成功
            if(CURRENT_LOCK.equals(firstNode)){
                return  true;
            }
            if (!lessThenMe.isEmpty()){
                WAIT_LOCK=lessThenMe.last();//获得比当前节点更小的最后一个节点，设置为WAIT_LOCK
            }


        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName()+"->"+"释放锁");
        try {
            zk.delete(CURRENT_LOCK,-1);
            CURRENT_LOCK=null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Condition newCondition() {
        return null;
    }
    //处理监听
    @Override
    public void process(WatchedEvent event) {

        if(countDownLatch!=null){
            this.countDownLatch.countDown();
        }

    }
}
