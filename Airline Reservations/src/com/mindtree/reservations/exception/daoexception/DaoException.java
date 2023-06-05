package com.mindtree.reservations.exception.daoexception;

import com.mindtree.reservations.exception.serviceexception.ServiceException;

public class DaoException extends ServiceException{

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
