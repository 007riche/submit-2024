
package remote.partners;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the remote.partners package. 
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

    private final static QName _SignUpUser_QNAME = new QName("http://services.booking.service.web/", "signUpUser");
    private final static QName _SignUpUserResponse_QNAME = new QName("http://services.booking.service.web/", "signUpUserResponse");
    private final static QName _UpdateCredentials_QNAME = new QName("http://services.booking.service.web/", "updateCredentials");
    private final static QName _UpdateCredentialsResponse_QNAME = new QName("http://services.booking.service.web/", "updateCredentialsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: remote.partners
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SignUpUser }
     * 
     */
    public SignUpUser createSignUpUser() {
        return new SignUpUser();
    }

    /**
     * Create an instance of {@link SignUpUserResponse }
     * 
     */
    public SignUpUserResponse createSignUpUserResponse() {
        return new SignUpUserResponse();
    }

    /**
     * Create an instance of {@link UpdateCredentials }
     * 
     */
    public UpdateCredentials createUpdateCredentials() {
        return new UpdateCredentials();
    }

    /**
     * Create an instance of {@link UpdateCredentialsResponse }
     * 
     */
    public UpdateCredentialsResponse createUpdateCredentialsResponse() {
        return new UpdateCredentialsResponse();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUpUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignUpUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "signUpUser")
    public JAXBElement<SignUpUser> createSignUpUser(SignUpUser value) {
        return new JAXBElement<SignUpUser>(_SignUpUser_QNAME, SignUpUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUpUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SignUpUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "signUpUserResponse")
    public JAXBElement<SignUpUserResponse> createSignUpUserResponse(SignUpUserResponse value) {
        return new JAXBElement<SignUpUserResponse>(_SignUpUserResponse_QNAME, SignUpUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCredentials }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateCredentials }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "updateCredentials")
    public JAXBElement<UpdateCredentials> createUpdateCredentials(UpdateCredentials value) {
        return new JAXBElement<UpdateCredentials>(_UpdateCredentials_QNAME, UpdateCredentials.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCredentialsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateCredentialsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.booking.service.web/", name = "updateCredentialsResponse")
    public JAXBElement<UpdateCredentialsResponse> createUpdateCredentialsResponse(UpdateCredentialsResponse value) {
        return new JAXBElement<UpdateCredentialsResponse>(_UpdateCredentialsResponse_QNAME, UpdateCredentialsResponse.class, null, value);
    }

}
