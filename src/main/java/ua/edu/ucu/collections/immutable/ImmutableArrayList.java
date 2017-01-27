package ua.edu.ucu.collections.immutable;

public class ImmutableArrayList implements ImmutableList {

    private Object[] array;

    public ImmutableArrayList() {
        array = new Object[0];
    }

    private ImmutableArrayList(Object[] oldArr, int index, Object[] c) {
        int len = oldArr.length + c.length;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArr[i];
        }
        for (int i = 0; i < c.length; i++) {
            array[index + i] = c[i];
        }
        for (int i = index; i < oldArr.length; i++) {
            array[i + c.length] = oldArr[i];
        }
    }

    private ImmutableArrayList(Object[] oldArr, Object e) {
        int len = oldArr.length + 1;
        array = new Object[len];
        for (int i = 0; i < oldArr.length; i++) {
            array[i] = oldArr[i];
        }
        array[len - 1] = e;
    }

    private ImmutableArrayList(Object[] oldArr, int index, Object e) {
        int len = oldArr.length + 1;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArr[i];
        }
        array[index] = e;

        for (int i = index; i < oldArr.length; i++) {
            array[i + 1] = oldArr[i];
        }
    }

    private ImmutableArrayList(Object[] oldArr, Object[] c) {
        int len = oldArr.length + c.length;
        array = new Object[len];
        for (int i = 0; i < oldArr.length; i++) {
            array[i] = oldArr[i];
        }
        for (int i = 0; i < c.length; i++) {
            array[i + oldArr.length] = c[i];
        }
    }
    
    private ImmutableArrayList(Object[] oldArr, int index) {
        int len = oldArr.length - 1;
        array = new Object[len];
        for (int i = 0; i < index; i++) {
            array[i] = oldArr[i];
        }

        for (int i = index; i < oldArr.length - 1; i++) {
            array[i] = oldArr[i + 1];
        }
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return new ImmutableArrayList(array, e);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        try {
            if (index > array.length) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableArrayList(array, index, e);
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return new ImmutableArrayList(array, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        try {
            if (index > array.length) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableArrayList(array, index, c);
    }

    @Override
    public Object get(int index) {
        try {
            if (index >= array.length) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }

        return array[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        return new ImmutableArrayList(array, index);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        return remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public String toString() {
        String res = " ";

        for (int i = 0; i < array.length - 1; i++) {
            res += array[i].toString() + ", ";
        }
        res += array[array.length - 1].toString();
        return res;
    }
}
