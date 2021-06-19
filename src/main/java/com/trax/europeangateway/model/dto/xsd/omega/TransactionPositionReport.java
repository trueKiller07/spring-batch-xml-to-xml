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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionPositionReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionPositionReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processingDetails" type="{http://deutsche-boerse.com/DBRegHub}processingDetails"/>
 *         &lt;element name="configurableFields" type="{http://deutsche-boerse.com/DBRegHub}configurableFields" minOccurs="0"/>
 *         &lt;element name="mifir" type="{http://deutsche-boerse.com/DBRegHub}mifirDetails" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionPositionReport", propOrder = {
    "processingDetails",
    "configurableFields",
    "mifir"
})
@XmlRootElement(name = "transaction")
public class TransactionPositionReport {

    @XmlElement(required = true)
    protected ProcessingDetails processingDetails;
    protected ConfigurableFields configurableFields;
    protected MifirDetails mifir;

    /**
     * Gets the value of the processingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingDetails }
     *     
     */
    public ProcessingDetails getProcessingDetails() {
        return processingDetails;
    }

    /**
     * Sets the value of the processingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingDetails }
     *     
     */
    public void setProcessingDetails(ProcessingDetails value) {
        this.processingDetails = value;
    }

    /**
     * Gets the value of the configurableFields property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurableFields }
     *     
     */
    public ConfigurableFields getConfigurableFields() {
        return configurableFields;
    }

    /**
     * Sets the value of the configurableFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurableFields }
     *     
     */
    public void setConfigurableFields(ConfigurableFields value) {
        this.configurableFields = value;
    }

    /**
     * Gets the value of the mifir property.
     * 
     * @return
     *     possible object is
     *     {@link MifirDetails }
     *     
     */
    public MifirDetails getMifir() {
        return mifir;
    }

    /**
     * Sets the value of the mifir property.
     * 
     * @param value
     *     allowed object is
     *     {@link MifirDetails }
     *     
     */
    public void setMifir(MifirDetails value) {
        this.mifir = value;
    }

}
