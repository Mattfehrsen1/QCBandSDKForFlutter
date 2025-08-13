package com.app.watch.keeping.entity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface ICactusInterface extends IInterface {

    public static class Default implements ICactusInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void connectionTimes(int i) throws RemoteException {
        }

        @Override // com.app.watch.keeping.entity.ICactusInterface
        public void wakeup(CactusConfig cactusConfig) throws RemoteException {
        }
    }

    void connectionTimes(int i) throws RemoteException;

    void wakeup(CactusConfig cactusConfig) throws RemoteException;

    public static abstract class Stub extends Binder implements ICactusInterface {
        private static final String DESCRIPTOR = "com.app.watch.keeping.entity.ICactusInterface";
        static final int TRANSACTION_connectionTimes = 2;
        static final int TRANSACTION_wakeup = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICactusInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICactusInterface)) {
                return (ICactusInterface) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                wakeup(parcel.readInt() != 0 ? CactusConfig.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            connectionTimes(parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ICactusInterface {
            public static ICactusInterface sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.app.watch.keeping.entity.ICactusInterface
            public void wakeup(CactusConfig cactusConfig) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cactusConfig != null) {
                        parcelObtain.writeInt(1);
                        cactusConfig.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wakeup(cactusConfig);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.app.watch.keeping.entity.ICactusInterface
            public void connectionTimes(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectionTimes(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICactusInterface iCactusInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCactusInterface == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCactusInterface;
            return true;
        }

        public static ICactusInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
