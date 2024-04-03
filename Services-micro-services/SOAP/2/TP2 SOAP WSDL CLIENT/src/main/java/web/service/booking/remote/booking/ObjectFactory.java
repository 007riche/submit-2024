
package remote.booking;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the remote.booking package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MakeBooking_QNAME = new QName("http://services.booking.service.web/", "makeBooking");
    private final static QName _MakeBookingResponse_QNAME = new QName("http://services.booking.service.web/", "makeBookingResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: remote.booking
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakeBooking }
     * 
     */
    public MakeBooking createMakeBooking() {
        return new MakeBooking();
    }

    /**
     * Create an instance of {@link MakeBookingResponse }
     * 
     */
    public MakeBookingResponse createMakeBookingResponse() {
        return new MakeBookingResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBooking }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeBooking }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "makeBooking")
    public JAXBElement<MakeBooking> createMakeBooking(MakeBooking value) {
        return new JAXBElement<MakeBooking>(_MakeBooking_QNAME, MakeBooking.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeBookingResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakeBookingResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "makeBookingResponse")
    public JAXBElement<MakeBookingResponse> createMakeBookingResponse(MakeBookingResponse value) {
        return new JAXBElement<MakeBookingResponse>(_MakeBookingResponse_QNAME, MakeBookingResponse.class, null, value);
    }

}
