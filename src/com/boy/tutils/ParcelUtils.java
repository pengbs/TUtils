package com.boy.tutils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelUtils {
    public static final int EXIST_SEPARATOR = 1;
    public static final int NON_SEPARATOR = 0;

    public ParcelUtils() {
    }

    public static void writeToParcel(Parcel out, String obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeString(obj);
        } else {
            out.writeInt(0);
        }

    }

    public static void writeToParcel(Parcel out, Long obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeLong(obj.longValue());
        } else {
            out.writeInt(0);
        }

    }

    public static void writeToParcel(Parcel out, Integer obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeInt(obj.intValue());
        } else {
            out.writeInt(0);
        }

    }

    public static void writeToParcel(Parcel out, Float obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeFloat(obj.floatValue());
        } else {
            out.writeInt(0);
        }

    }

    public static void writeToParcel(Parcel out, Map obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeMap(obj);
        } else {
            out.writeInt(0);
        }

    }

    public static void writeToParcel(Parcel out, Date obj) {
        if(obj != null) {
            out.writeInt(1);
            out.writeLong(obj.getTime());
        } else {
            out.writeInt(0);
        }

    }

    public static Float readFloatFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?Float.valueOf(in.readFloat()):null;
    }

    public static Date readDateFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?new Date(in.readLong()):null;
    }

    public static Integer readIntFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?Integer.valueOf(in.readInt()):null;
    }

    public static Long readLongFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?Long.valueOf(in.readLong()):null;
    }

    public static String readFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?in.readString():null;
    }

    public static Map readMapFromParcel(Parcel in) {
        int flag = in.readInt();
        return flag == 1?in.readHashMap(HashMap.class.getClassLoader()):null;
    }

    public static <T extends Parcelable> T readFromParcel(Parcel in, Class<T> cls) {
        int flag = in.readInt();
        return (T) (flag == 1?in.readParcelable(cls.getClassLoader()):null);
    }

    public static <T extends Parcelable> void writeToParcel(Parcel out, T model) {
        if(model != null) {
            out.writeInt(1);
            out.writeParcelable(model, 0);
        } else {
            out.writeInt(0);
        }

    }

    public static <T extends List<?>> void writeToParcel(Parcel out, T model) {
        if(model != null) {
            out.writeInt(1);
            out.writeList(model);
        } else {
            out.writeInt(0);
        }

    }

    public static <T> ArrayList<T> readListFromParcel(Parcel in, Class<T> cls) {
        int flag = in.readInt();
        return flag == 1?in.readArrayList(cls.getClassLoader()):null;
    }

    public static void writeListToParcel(Parcel out, List<?> collection) {
        if(collection != null) {
            out.writeInt(1);
            out.writeList(collection);
        } else {
            out.writeInt(0);
        }

    }

    public static <T extends Parcelable> T bytesToParcelable(byte[] data, Class<T> cls) {
        if(data != null && data.length != 0) {
            Parcel in = Parcel.obtain();
            in.unmarshall(data, 0, data.length);
            in.setDataPosition(0);
            Parcelable t = readFromParcel(in, cls);
            in.recycle();
            return (T) t;
        } else {
            return null;
        }
    }

    public static byte[] parcelableToByte(Parcelable model) {
        if(model == null) {
            return null;
        } else {
            Parcel parcel = Parcel.obtain();
            writeToParcel(parcel, model);
            return parcel.marshall();
        }
    }

    public static <T extends Parcelable> List<T> bytesToParcelableList(byte[] data, Class<T> cls) {
        if(data != null && data.length != 0) {
            Parcel in = Parcel.obtain();
            in.unmarshall(data, 0, data.length);
            in.setDataPosition(0);
            ArrayList t = readListFromParcel(in, cls);
            in.recycle();
            return t;
        } else {
            return null;
        }
    }

    public static byte[] parcelableListToByte(List<? extends Parcelable> list) {
        if(list == null) {
            return null;
        } else {
            Parcel parcel = Parcel.obtain();
            writeListToParcel(parcel, list);
            return parcel.marshall();
        }
    }
}
