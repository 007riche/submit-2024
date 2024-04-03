
package remote.browse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for offer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="offer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="availabilityBegin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="checkoutDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="imgURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="numberBed" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="numberPerson" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="roomNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "offer", propOrder = {
    "availabilityBegin",
    "checkoutDate",
    "imgURL",
    "numberBed",
    "numberPerson",
    "offerId",
    "price",
    "roomNumber"
})
public class Offer {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar availabilityBegin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar checkoutDate;
    protected String imgURL;
    protected int numberBed;
    protected int numberPerson;
    protected String offerId;
    protected Double price;
    protected int roomNumber;

    /**
     * Gets the value of the availabilityBegin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAvailabilityBegin() {
        return availabilityBegin;
    }

    /**
     * Sets the value of the availabilityBegin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAvailabilityBegin(XMLGregorianCalendar value) {
        this.availabilityBegin = value;
    }

    /**
     * Gets the value of the checkoutDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCheckoutDate() {
        return checkoutDate;
    }

    /**
     * Sets the value of the checkoutDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCheckoutDate(XMLGregorianCalendar value) {
        this.checkoutDate = value;
    }

    /**
     * Gets the value of the imgURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgURL() {
        return imgURL;
    }

    /**
     * Sets the value of the imgURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgURL(String value) {
        this.imgURL = value;
    }

    /**
     * Gets the value of the numberBed property.
     * 
     */
    public int getNumberBed() {
        return numberBed;
    }

    /**
     * Sets the value of the numberBed property.
     * 
     */
    public void setNumberBed(int value) {
        this.numberBed = value;
    }

    /**
     * Gets the value of the numberPerson property.
     * 
     */
    public int getNumberPerson() {
        return numberPerson;
    }

    /**
     * Sets the value of the numberPerson property.
     * 
     */
    public void setNumberPerson(int value) {
        this.numberPerson = value;
    }

    /**
     * Gets the value of the offerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferId(String value) {
        this.offerId = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * Gets the value of the roomNumber property.
     * 
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the value of the roomNumber property.
     * 
     */
    public void setRoomNumber(int value) {
        this.roomNumber = value;
    }

}
