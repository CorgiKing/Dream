package org.corgiking.serialize.kryo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoUtil {

	// 每个线程的 Kryo 实例
	private static final ThreadLocal<Kryo> kryoLocal = new ThreadLocal<Kryo>() {
		@Override
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			// Fix the NPE bug when deserializing Collections.
			((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
					.setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
			return kryo;
		}
	};

	/**
	 * 获得当前线程的 Kryo 实例
	 */
	public static Kryo getInstance() {
		return kryoLocal.get();
	}

	/**
	 * 将对象序列化为字节数组
	 *
	 */
	public static <T> byte[] writeObjectToByteArray(T obj) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);

		Kryo kryo = getInstance();
		kryo.writeObject(output, obj);
		output.flush();

		return baos.toByteArray();
	}

	/**
	 * 将字节数组反序列化为原对象
	 *
	 */
	public static <T> T readObjectFromByteArray(byte[] byteArray, Class<T> clazz) {
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		Input input = new Input(bais);

		Kryo kryo = getInstance();
		return kryo.readObject(input, clazz);
	}

}