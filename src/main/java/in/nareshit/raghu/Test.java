package in.nareshit.raghu;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class MyData {

	String code;

	public MyData(String code) {
		this.code=code;
	}

	@Override
	public String toString() {
		return "MyData [code=" + code + "]";
	}

}

public class Test {

	public static void main(String[] args) {
		List<String> data = Arrays.asList("hi","hello","one"); 

		Set<MyData> set = 
				data.stream()
				.map(a->new MyData(a))
				.collect(Collectors.toSet());
		
		set.forEach(System.out::println);
	}
}
