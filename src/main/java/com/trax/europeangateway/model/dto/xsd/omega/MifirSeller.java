//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.18 at 02:21:55 PM BST 
//


package com.trax.europeangateway.model.dto.xsd.omega;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mifirSeller complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mifirSeller">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mifirSellerDetails" type="{http://deutsche-boerse.com/DBRegHub}mifirSellerDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mifirSellerDecisionMakerDetails" type="{http://deutsche-boerse.com/DBRegHub}mifirSellerDecisionMakerDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mifirSeller", propOrder = {
    "mifirSellerDetails",
    "mifirSellerDecisionMakerDetails"
})
public class MifirSeller {

    protected List<MifirSellerDetails> mifirSellerDetails;
    protected List<MifirSellerDecisionMakerDetails> mifirSellerDecisionMakerDetails;

    /**
     * Gets the value of the mifirSellerDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mifirSellerDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMifirSellerDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MifirSellerDetails }
     * 
     * 
     */
    public List<MifirSellerDetails> getMifirSellerDetails() {
        if (mifirSellerDetails == null) {
            mifirSellerDetails = new ArrayList<MifirSellerDetails>();
        }
        return this.mifirSellerDetails;
    }

    /**
     * Gets the value of the mifirSellerDecisionMakerDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mifirSellerDecisionMakerDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMifirSellerDecisionMakerDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MifirSellerDecisionMakerDetails }
     * 
     * 
     */
    public List<MifirSellerDecisionMakerDetails> getMifirSellerDecisionMakerDetails() {
        if (mifirSellerDecisionMakerDetails == null) {
            mifirSellerDecisionMakerDetails = new ArrayList<MifirSellerDecisionMakerDetails>();
        }
        return this.mifirSellerDecisionMakerDetails;
    }

}