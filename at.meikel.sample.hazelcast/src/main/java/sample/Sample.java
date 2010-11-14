package sample;

import java.util.Collection;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.Instance;
import com.hazelcast.core.InstanceEvent;
import com.hazelcast.core.InstanceListener;

public class Sample implements InstanceListener {
	public static void main(String[] args) {
		Sample sample = new Sample();
		Hazelcast.addInstanceListener(sample);
		Collection<Instance> instances = Hazelcast.getInstances();
		for (Instance instance : instances) {
			System.out.println(instance.getInstanceType() + ","
					+ instance.getId());
		}
	}

	public void instanceCreated(InstanceEvent event) {
		Instance instance = event.getInstance();
		System.out.println("Created " + instance.getInstanceType() + ","
				+ instance.getId());
	}

	public void instanceDestroyed(InstanceEvent event) {
		Instance instance = event.getInstance();
		System.out.println("Destroyed " + instance.getInstanceType() + ","
				+ instance.getId());
	}
}
