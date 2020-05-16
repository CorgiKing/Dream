package org.corgiking.io;

import java.nio.ByteBuffer;
import java.text.MessageFormat;

/**
 * mark 对一个position进行标记，便于下次返回到该position
 * position 当前位置
 * limit 读/写不能超过的下标位置，
 * capacity buf的容量
 *
 * 0 <= mark <= position <= limit <= capacity
 */
public class BufferTest {

	public static void main(String[] args) {
		//新建
		//position=0, limit=10, capacity=10
		ByteBuffer buf = ByteBuffer.allocate(10);

		//写入
		//position=2, limit=10, capacity=10
		buf.put(new byte[]{1,2});
		
		//准备读取 limit = position; position = 0; mark = -1;
		//position=0, limit=2, capacity=10
		buf.flip();
		
		//读取数据
		//position=2, limit=2, capacity=10
		while(buf.hasRemaining()){
			byte b = buf.get();
			System.out.println(b);
		}
		
		//position=0, limit=10, capacity=10
		buf.clear();
		
		//position=3, limit=10, capacity=10
		buf.put(new byte[]{3,4,5});
		
		//position=0, limit=3, capacity=10
		buf.flip();
		
		//position=1, limit=3, capacity=10
		buf.get();
		
		//将所有未读的数据拷贝到Buffer起始处,将position设到最后一个未读元素正后面,limit设置成capacity
		//position=2, limit=10, capacity=10
		buf.compact();
		
		//标记当前position(2)
		buf.mark();
		
		//position=3, limit=10, capacity=10
		buf.get();
		
		//恢复到标记的position
		//position=2, limit=10, capacity=10
		buf.reset();
		
		//position设为0，limit不变，一般在把数据重写入Buffer前调用。
		//position=0, limit=10, capacity=10
		buf.rewind();


		System.out.println(MessageFormat.format("position={0}, limit={1}, capacity={2}",buf.position(),buf.limit(),buf.capacity()));
	}

}
