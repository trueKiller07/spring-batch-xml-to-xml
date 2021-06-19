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
 * <p>Java class for referencePartyDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="referencePartyDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="executingEntityId" type="{http://deutsche-boerse.com/DBRegHub}LEId"/>
 *         &lt;element name="internalPartyId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="35"/>
 *               &lt;pattern value="[A-Za-z 0-9]{1,35}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="code" type="{http://deutsche-boerse.com/DBRegHub}partyCodeType" minOccurs="0"/>
 *         &lt;element name="branchCountry" type="{http://deutsche-boerse.com/DBRegHub}Country_Code" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://deutsche-boerse.com/DBRegHub}nameType" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://deutsche-boerse.com/DBRegHub}nameType" minOccurs="0"/>
 *         &lt;element name="birthdate" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="(17|18|19|20|21)[0-9]{2}(-)((0)[1-9]|(1)[012])(-)((0)[1-9]|(1|2)[0-9]|(3)[01])"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referencePartyDetails", propOrder = {
    "executingEntityId",
    "internalPartyId",
    "code",
    "branchCountry",
    "firstName",
    "surname",
    "birthdate"
})
public class ReferencePartyDetails {

    @XmlElement(required = true)
    protected LEId executingEntityId;
    @XmlElement(required = true)
    protected String internalPartyId;
    protected PartyCodeType code;
    protected String branchCountry;
    protected String firstName;
    protected String surname;
    protected String birthdate;

    /**
     * Gets the value of the executingEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link LEId }
     *     
     */
    public LEId getExecutingEntityId() {
        return executingEntityId;
    }

    /**
     * Sets the value of the executingEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link LEId }
     *     
     */
    public void setExecutingEntityId(LEId value) {
        this.executingEntityId = value;
    }

    /**
     * Gets the value of the internalPartyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalPartyId() {
        return internalPartyId;
    }

    /**
     * Sets the value of the internalPartyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalPartyId(String value) {
        this.internalPartyId = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link PartyCodeType }
     *     
     */
    public PartyCodeType getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyCodeType }
     *     
     */
    public void setCode(PartyCodeType value) {
        this.code = value;
    }

    /**
     * Gets the value of the branchCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchCountry() {
        return branchCountry;
    }

    /**
     * Sets the value of the branchCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchCountry(String value) {
        this.branchCountry = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthdate(String value) {
        this.birthdate = value;
    }

}