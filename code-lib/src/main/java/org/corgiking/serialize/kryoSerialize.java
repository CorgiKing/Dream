package org.corgiking.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class kryoSerialize {

	public static void main(String[] args) throws IOException {
		stringSerialize();
	}
	
	public static void userSerialize() {
		Kryo kryo = new Kryo();

		User u = new User();
		u.setId(0);
		u.setName("yy");
		Map<String, String> map = new HashMap<>();
		map.put("tes", "tes");
		map.put("ttt", "ttt");
		u.setMap(map);
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		u.setList(list);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);
		kryo.writeObject(output, u);
		output.flush();
        output.close();
		byte[] bys = baos.toByteArray();
		System.out.println(bys.length);
		String string = new String(Base64.getEncoder().encode(bys));
		System.out.println(string);
	}

	public static void stringSerialize() {
		Kryo kryo = new Kryo();
		String testStr = "abcdefghijklmnopqistuvwxyz";
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Output output = new Output(baos);
		output.writeString(testStr);
		output.flush();
		byte[] bys = baos.toByteArray();
		System.out.println(bys.length);
		
		Input input = new Input(new ByteArrayInputStream(bys));
		String ret = input.readString();
		System.out.println(ret);
	}

}
