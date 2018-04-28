package demo;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * 自定义分区策略
 * @author fibbery
 * @date 18/3/20
 */
public class SelfPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        if (keyBytes == null || !(key instanceof String)) {
            throw new InvalidRecordException("We expect all messages to have customer name as key");
        }
        if (((String) key).equals("bananer")) {
            return partitionInfos.size();
        }

        return Math.abs(Utils.murmur2(keyBytes)) % (partitionInfos.size() - 1);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
