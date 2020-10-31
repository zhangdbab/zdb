package demo.zookeeper.rpc.client.loadbalence;

import java.util.List;

/**
 * Created by DJ009828 on 2020/10/31 16:23
 */
public  interface  LoadBalance {
    String selectHost(List<String> repos);

}
