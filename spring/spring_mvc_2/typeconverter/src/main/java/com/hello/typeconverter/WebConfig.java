package com.hello.typeconverter;

import com.hello.typeconverter.converter.IntegerToStringConverter;
import com.hello.typeconverter.converter.IpPortToStringConverter;
import com.hello.typeconverter.converter.StringToIntegerConverter;
import com.hello.typeconverter.converter.StringToIpPortConverter;
import com.hello.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 주선 처리 : 우선순위 때문에  [Converter 가 Formatter 보다 우선순위가 더 높다]
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
