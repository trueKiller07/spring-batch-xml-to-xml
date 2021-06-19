//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:21:55 PM BST 
//


package com.trax.europeangateway.model.dto.xsd.omega;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mifirUnderlyingInstrument complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mifirUnderlyingInstrument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="underlyingInstrumentDirection" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1"/>
 *               &lt;enumeration value="+"/>
 *               &lt;enumeration value="-"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="underlyingInstrumentCode" type="{http://deutsche-boerse.com/DBRegHub}ISIN"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mifirUnderlyingInstrument", propOrder = {
    "underlyingInstrumentDirection",
    "underlyingInstrumentCode"
})
public class MifirUnderlyingInstrument {

    protected String underlyingInstrumentDirection;
    @XmlElement(required = true)
    protected String underlyingInstrumentCode;

    /**
     * Gets the value of the underlyingInstrumentDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderlyingInstrumentDirection() {
        return underlyingInstrumentDirection;
    }

    /**
     * Sets the value of the underlyingInstrumentDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderlyingInstrumentDirection(String value) {
        this.underlyingInstrumentDirection = value;
    }

    /**
     * Gets the value of the underlyingInstrumentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderlyingInstrumentCode() {
        return underlyingInstrumentCode;
    }

    /**
     * Sets the value of the underlyingInstrumentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderlyingInstrumentCode(String value) {
        this.underlyingInstrumentCode = value;
    }

}