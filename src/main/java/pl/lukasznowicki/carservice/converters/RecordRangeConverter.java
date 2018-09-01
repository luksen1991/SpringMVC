package pl.lukasznowicki.carservice.converters;

import org.springframework.core.convert.converter.Converter;

import pl.lukasznowicki.carservice.dto.RocordRange;

public class RecordRangeConverter implements Converter<String,RocordRange> {

	public RocordRange convert(String source) {
		return new RocordRange(Integer.parseInt(source.substring(0,source.indexOf("-")))
							  ,Integer.parseInt(source.substring(source.indexOf("-")+1)));
	}

}
