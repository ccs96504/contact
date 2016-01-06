package contactorganizer.contactorganizer;

import android.net.Uri;

/**
 * Created by User on 2016/1/3.
 */
public class Contact {
    private String _name, _phone, _email, _address;
    private int _id;

    public Contact(int id ,String name, String phone, String email, String address) {
        _id=id;
        _name=name;
        _phone=phone;
        _email=email;
        _address=address;
    }
    public int getid(){
        return _id;
    }
    public String getName() {
        return _name;
    }

    public String getphone(){
        return _phone;
    }

    public String getAddress() {
        return _address;
    }

    public String getEmail() {
        return _email;
    }


}
