package com.hello.typeconverter.formatter;

import com.hello.typeconverter.converter.IpPortToStringConverter;
import com.hello.typeconverter.converter.StringToIpPortConverter;
import com.hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattingConversionServiceTest {

    @Test
    public void formattingConversionService() throws Exception{
        // given
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        // 컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        // 포멧터 등록
        conversionService.addFormatter(new MyNumberFormatter());
        // when

        // 컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        // 포멧터 사용
        String convert = conversionService.convert(1000, String.class);
        // then

        // 컨버터 테스트
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
        // 포멧터 테스트
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000L);
    }
}
