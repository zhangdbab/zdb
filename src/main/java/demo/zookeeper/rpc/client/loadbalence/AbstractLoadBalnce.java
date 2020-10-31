package demo.zookeeper.rpc.client.loadbalence;

import java.util.List;

/**
 * Created by DJ009828 on 2020/10/31 16:28
 */
public abstract  class AbstractLoadBalnce  implements  LoadBalance{
    @Override
    public String selectHost(List<String> repos) {
        if(repos.size()==0||repos==null){
            return  null ;

        }
        if (repos.size()==1){

            return repos.get(0);
        }

        return doSelect(repos);
    }

    protected   abstract String doSelect(List<String> repos);


}
