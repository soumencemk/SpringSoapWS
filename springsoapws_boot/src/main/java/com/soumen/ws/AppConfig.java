/*Configuration Class: Use WsConfigurerAdapter , @EnableWs, DefaultWsdl11Definition, XsdSchema
The configuration class should be annotated with @EnableWs that will provide web service configuration.
We need to override WsConfigurerAdapter to get required WsConfigurer methods. The bean name for DefaultWsdl11Definition is must because
this bean name will be the part of WSDL URL as students.wsdl.

@EnableWs : Provides spring web service configuration.
WsConfigurerAdapter : This class is an adapter class that contains only required methods of WsConfigurer.
DefaultWsdl11Definition : Creates SOAP for the given XSD schema.
XsdSchema : Abstraction for XSD schema.
*/

package com.soumen.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("com.soumen")
public class AppConfig extends WsConfigurerAdapter {

    @Bean(name = "students")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("StudentsPort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://soumen.com/soap");
        wsdl11Definition.setSchema(studentsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema studentsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("student.xsd"));
    }

}
