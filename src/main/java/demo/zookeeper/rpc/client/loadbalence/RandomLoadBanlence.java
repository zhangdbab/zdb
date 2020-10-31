package demo.zookeeper.rpc.client.loadbalence;

import java.util.List;
import java.util.Random;

/**
 * Created by DJ009828 on 2020/10/31 16:31
 * 随机负载实现
 */
public class  RandomLoadBanlence extends  AbstractLoadBalnce{
    /**
     *
     * @param repos
     * @return
     */
    @Override
    protected String doSelect(List<String> repos) {
        int len =repos.size();
        Random random =new Random();
        return repos.get(random.nextInt(len));

    }
}
