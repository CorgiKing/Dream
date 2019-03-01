package org.corgiking.test.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.corgiking.util.ParamTypeUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {
		OneEntity a = new OneEntity("test", 110);
		List<OneEntity> aList = new ArrayList<>();
		aList.add(a);
		aList.add(a);
		aList.add(a);

		PageResult<OneEntity> pageResult = new PageResult<>(3, aList);
		ResultObj<PageResult<OneEntity>> resultObj = new ResultObj<>(0, "ok", pageResult);

		ResultObj<OneEntity> resultObj2 = new ResultObj(0, "ok", a);
		
		ParamTypeUtil.findTypeParamClass(pageResult, PageResult.class, "T");

		Gson gson = new Gson();
		
		
		{
			String json = gson.toJson(resultObj2);
			System.out.println(json);
			
			Type type2 = new TypeToken<ResultObj<OneEntity>>() {}.getType();
			System.out.println(type2.getTypeName());
			
			
			Type type = new ParameterizedTypeImpl(ResultObj.class, new Class[] { OneEntity.class });
			System.out.println(type.getTypeName());
			

			ResultObj<OneEntity> resultObjTest = gson.fromJson(json, type);

			System.out.println(resultObjTest.getData().str);
			
		}


	}

	/**
	 * 多层泛型测试
	 */
	private static void manyTest() {
		Gson gson = new Gson();

		OneEntity a = new OneEntity("test", 110);
		List<OneEntity> aList = new ArrayList<>();
		aList.add(a);
		aList.add(a);
		aList.add(a);

		PageResult<OneEntity> pageResult = new PageResult<>(3, aList);

		ResultObj<PageResult<OneEntity>> resultObj = new ResultObj<>(0, "ok", pageResult);

		String json = gson.toJson(resultObj);
		System.out.println("生成的json: " + json);

		Type type = new TypeToken<ResultObj<PageResult<OneEntity>>>() {
		}.getType();
		ResultObj<PageResult<OneEntity>> jsonObj = gson.fromJson(json, type);
		System.out.println(jsonObj);
	}

	/**
	 * list泛型测试
	 */
	private static void listTest() {
		Gson gson = new Gson();

		OneEntity a = new OneEntity("test", 110);
		List<OneEntity> aList = new ArrayList<>();
		aList.add(a);
		aList.add(a);
		aList.add(a);

		String json = gson.toJson(aList);
		System.out.println("生成的json: " + json);

		System.out.println("array:");
		OneEntity[] aArray = gson.fromJson(json, OneEntity[].class);
		for (OneEntity item : aArray) {
			System.out.println(item);
		}

		System.out.println("list:");
		// 因为TypeToken的无参构造函数是protected，所以加了大括号
		Type type = new TypeToken<List<OneEntity>>() {
		}.getType();
		List<OneEntity> aList2 = gson.fromJson(json, type);
		for (OneEntity item : aList2) {
			System.out.println(item);
		}
	}

}

class OneEntity {
	public String str;
	public int num;

	public OneEntity(String str, int num) {
		super();
		this.str = str;
		this.num = num;
	}

	@Override
	public String toString() {
		return "A [str=" + str + ", num=" + num + "]";
	}

}

class ResultObj<T> {
	public int status;
	public String msg;
	public T data;

	public ResultObj(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultObj [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}

class PageResult<T> {
	int total;
	List<T> data;

	public PageResult(int total, List<T> data) {
		super();
		this.total = total;
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PageResult [total=" + total + ", data=" + data + "]";
	}

}

class ParameterizedTypeImpl implements ParameterizedType {
	private final Class raw;
	private final Type[] args;

	public ParameterizedTypeImpl(Class raw, Type[] args) {
		this.raw = raw;
		this.args = args != null ? args : new Type[0];
	}

	@Override
	public Type[] getActualTypeArguments() {
		return args;
	}

	@Override
	public Type getRawType() {
		return raw;
	}

	@Override
	public Type getOwnerType() {
		return null;
	}
}
