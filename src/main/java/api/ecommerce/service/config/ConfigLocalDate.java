//package api.ecommerce.service.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class ConfigLocalDate implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(localDateFormatter());
//    }
//
//    @Bean
//    public LocalDateFormatter localDateFormatter() {
//        return new LocalDateFormatter("dd/MM/yyyy");
//    }
//
//    public static class LocalDateFormatter implements org.springframework.format.Formatter<LocalDate> {
//        private final DateTimeFormatter formatter;
//
//        public LocalDateFormatter(String dateFormat) {
//            this.formatter = DateTimeFormatter.ofPattern(dateFormat);
//        }
//
//        @Override
//        public LocalDate parse(String text, java.util.Locale locale) {
//            return LocalDate.parse(text, formatter);
//        }
//
//        @Override
//        public String print(LocalDate object, java.util.Locale locale) {
//            return formatter.format(object);
//        }
//    }
//}
