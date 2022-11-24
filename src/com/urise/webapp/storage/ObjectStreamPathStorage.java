package com.urise.webapp.storage;

import com.urise.webapp.storage.serializer.StreamSerializer;

public class ObjectStreamPathStorage extends PathStorage {
    protected ObjectStreamPathStorage(String dir, StreamSerializer streamSerializer) {
        super(dir, streamSerializer);
    }

    /*@Override
    protected void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }*/
}