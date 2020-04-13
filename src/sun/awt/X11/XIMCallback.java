// This file is an automatically generated file, please do not edit this file, modify the WrapperGenerator.java file instead !

package sun.awt.X11;

import sun.misc.*;

import sun.util.logging.PlatformLogger;
public class XIMCallback extends XWrapperBase { 
	private Unsafe unsafe = XlibWrapper.unsafe; 
	private final boolean should_free_memory;
	public static int getSize() { return 16; }
	public int getDataSize() { return getSize(); }

	long pData;

	public long getPData() { return pData; }


	public XIMCallback(long addr) {
		log.finest("Creating");
		pData=addr;
		should_free_memory = false;
	}


	public XIMCallback() {
		log.finest("Creating");
		pData = unsafe.allocateMemory(getSize());
		should_free_memory = true;
	}


	public void dispose() {
		log.finest("Disposing");
		if (should_free_memory) {
			log.finest("freeing memory");
			unsafe.freeMemory(pData); 
	}
		}
	public long get_client_data(int index) { log.finest(""); return Native.getLong(pData+0)+index*Native.getLongSize(); }
	public long get_client_data() { log.finest("");return Native.getLong(pData+0); }
	public void set_client_data(long v) { log.finest(""); Native.putLong(pData + 0, v); }
	public long get_callback(int index) { log.finest(""); return Native.getLong(pData+8)+index*Native.getLongSize(); }
	public long get_callback() { log.finest("");return Native.getLong(pData+8); }
	public void set_callback(long v) { log.finest(""); Native.putLong(pData + 8, v); }


	String getName() {
		return "XIMCallback"; 
	}


	String getFieldsAsString() {
		StringBuilder ret = new StringBuilder(80);

		ret.append("client_data = ").append( get_client_data() ).append(", ");
		ret.append("callback = ").append( get_callback() ).append(", ");
		return ret.toString();
	}


}



