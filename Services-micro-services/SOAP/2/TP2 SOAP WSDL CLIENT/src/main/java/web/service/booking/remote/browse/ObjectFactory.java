
package remote.browse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the remote.browse package. 
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

    private final static QName _BrowseAvailableRooms_QNAME = new QName("http://services.booking.service.web/", "browseAvailableRooms");
    private final static QName _BrowseAvailableRoomsResponse_QNAME = new QName("http://services.booking.service.web/", "browseAvailableRoomsResponse");
    private final static QName _GetImageURL_QNAME = new QName("http://services.booking.service.web/", "getImageURL");
    private final static QName _GetImageURLResponse_QNAME = new QName("http://services.booking.service.web/", "getImageURLResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: remote.browse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BrowseAvailableRooms }
     * 
     */
    public BrowseAvailableRooms createBrowseAvailableRooms() {
        return new BrowseAvailableRooms();
    }

    /**
     * Create an instance of {@link BrowseAvailableRoomsResponse }
     * 
     */
    public BrowseAvailableRoomsResponse createBrowseAvailableRoomsResponse() {
        return new BrowseAvailableRoomsResponse();
    }

    /**
     * Create an instance of {@link GetImageURL }
     * 
     */
    public GetImageURL createGetImageURL() {
        return new GetImageURL();
    }

    /**
     * Create an instance of {@link GetImageURLResponse }
     * 
     */
    public GetImageURLResponse createGetImageURLResponse() {
        return new GetImageURLResponse();
    }

    /**
     * Create an instance of {@link Offer }
     * 
     */
    public Offer createOffer() {
        return new Offer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrowseAvailableRooms }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BrowseAvailableRooms }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "browseAvailableRooms")
    public JAXBElement<BrowseAvailableRooms> createBrowseAvailableRooms(BrowseAvailableRooms value) {
        return new JAXBElement<BrowseAvailableRooms>(_BrowseAvailableRooms_QNAME, BrowseAvailableRooms.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BrowseAvailableRoomsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BrowseAvailableRoomsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "browseAvailableRoomsResponse")
    public JAXBElement<BrowseAvailableRoomsResponse> createBrowseAvailableRoomsResponse(BrowseAvailableRoomsResponse value) {
        return new JAXBElement<BrowseAvailableRoomsResponse>(_BrowseAvailableRoomsResponse_QNAME, BrowseAvailableRoomsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageURL }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImageURL }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "getImageURL")
    public JAXBElement<GetImageURL> createGetImageURL(GetImageURL value) {
        return new JAXBElement<GetImageURL>(_GetImageURL_QNAME, GetImageURL.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImageURLResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImageURLResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "getImageURLResponse")
    public JAXBElement<GetImageURLResponse> createGetImageURLResponse(GetImageURLResponse value) {
        return new JAXBElement<GetImageURLResponse>(_GetImageURLResponse_QNAME, GetImageURLResponse.class, null, value);
    }

}
