//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.12 at 07:28:56 PM IST 
//


package com.test.em.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CorrectedRecordType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorrectedRecordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processActivityHeader" type="{http://com.capgemini/cif}ProcessActivityHeaderType"/>
 *         &lt;element name="payloads" type="{http://com.capgemini/cif/iem}PayloadRecordType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrectedRecordType", propOrder = {
    "processActivityHeader",
    "payloads"
})
public class CorrectedRecordType {

    @XmlElement(required = true)
    protected ProcessActivityHeaderType processActivityHeader;
    protected List<PayloadRecordType> payloads;

    /**
     * Gets the value of the processActivityHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessActivityHeaderType }
     *     
     */
    public ProcessActivityHeaderType getProcessActivityHeader() {
        return processActivityHeader;
    }

    /**
     * Sets the value of the processActivityHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessActivityHeaderType }
     *     
     */
    public void setProcessActivityHeader(ProcessActivityHeaderType value) {
        this.processActivityHeader = value;
    }

    /**
     * Gets the value of the payloads property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payloads property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayloads().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PayloadRecordType }
     * 
     * 
     */
    public List<PayloadRecordType> getPayloads() {
        if (payloads == null) {
            payloads = new ArrayList<PayloadRecordType>();
        }
        return this.payloads;
    }

}
