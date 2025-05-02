package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // @Builder 사용시에는 @AllArgsConstructor,@NoArgsConstructor 세트로기입
@AllArgsConstructor
@NoArgsConstructor
public class SampleDTO {

	private String name;
	private int age;
	public static SampleDTOBuilder builder() {
		return null;
	}

	//public SampleDTO{
		
	//}
}
