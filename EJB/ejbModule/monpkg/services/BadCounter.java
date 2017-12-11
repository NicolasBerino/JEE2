package monpkg.services;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class BadCounter extends Exception {

    public BadCounter(String msg) {
        super(msg);
    }

}