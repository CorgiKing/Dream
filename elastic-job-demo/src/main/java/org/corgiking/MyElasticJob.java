package org.corgiking;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticJob implements SimpleJob {

	@Override
	public void execute(ShardingContext context) {
		switch (context.getShardingItem()) {
		case 0:
			System.out.println("I am zore !");
			break;
		case 1:
			System.out.println("I am one !");
			break;
		case 2:
			System.out.println("I am two !");
			break;
		default:
			break;
		}
	}

}
