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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mifirCounterpartyDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mifirCounterpartyDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="executingEntityId" type="{http://deutsche-boerse.com/DBRegHub}LEId"/>
 *         &lt;element name="mifidInvestmentFirm" type="{http://deutsche-boerse.com/DBRegHub}YesNo" minOccurs="0"/>
 *         &lt;element name="branchMembershipCountry" type="{http://deutsche-boerse.com/DBRegHub}Country_Code" minOccurs="0"/>
 *         &lt;element name="buyer" type="{http://deutsche-boerse.com/DBRegHub}mifirBuyer" minOccurs="0"/>
 *         &lt;element name="seller" type="{http://deutsche-boerse.com/DBRegHub}mifirSeller" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mifirCounterpartyDetails", propOrder = {
    "executingEntityId",
    "mifidInvestmentFirm",
    "branchMembershipCountry",
    "buyer",
    "seller"
})
public class MifirCounterpartyDetails {

    @XmlElement(required = true)
    protected LEId executingEntityId;
    @XmlSchemaType(name = "string")
    protected YesNo mifidInvestmentFirm;
    protected String branchMembershipCountry;
    protected MifirBuyer buyer;
    protected MifirSeller seller;

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
     * Gets the value of the mifidInvestmentFirm property.
     * 
     * @return
     *     possible object is
     *     {@link YesNo }
     *     
     */
    public YesNo getMifidInvestmentFirm() {
        return mifidInvestmentFirm;
    }

    /**
     * Sets the value of the mifidInvestmentFirm property.
     * 
     * @param value
     *     allowed object is
     *     {@link YesNo }
     *     
     */
    public void setMifidInvestmentFirm(YesNo value) {
        this.mifidInvestmentFirm = value;
    }

    /**
     * Gets the value of the branchMembershipCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchMembershipCountry() {
        return branchMembershipCountry;
    }

    /**
     * Sets the value of the branchMembershipCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchMembershipCountry(String value) {
        this.branchMembershipCountry = value;
    }

    /**
     * Gets the value of the buyer property.
     * 
     * @return
     *     possible object is
     *     {@link MifirBuyer }
     *     
     */
    public MifirBuyer getBuyer() {
        return buyer;
    }

    /**
     * Sets the value of the buyer property.
     * 
     * @param value
     *     allowed object is
     *     {@link MifirBuyer }
     *     
     */
    public void setBuyer(MifirBuyer value) {
        this.buyer = value;
    }

    /**
     * Gets the value of the seller property.
     * 
     * @return
     *     possible object is
     *     {@link MifirSeller }
     *     
     */
    public MifirSeller getSeller() {
        return seller;
    }

    /**
     * Sets the value of the seller property.
     * 
     * @param value
     *     allowed object is
     *     {@link MifirSeller }
     *     
     */
    public void setSeller(MifirSeller value) {
        this.seller = value;
    }

}
