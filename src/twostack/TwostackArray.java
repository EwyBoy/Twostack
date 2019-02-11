package twostack;

import exceptions.TwostackEmptyException;
import exceptions.TwostackFullException;

public class TwostackArray<E> implements Twostack<E> {

    private E[] twostack;

    private int leftArraySize;
    private int rightArraySize;

    public TwostackArray() {
        twostack = (E[]) new Object[101];
        leftArraySize = 0;
        rightArraySize = twostack.length;
    }

    public TwostackArray(int maxStackSize) {
        twostack = (E[]) new Object[maxStackSize + 1];
        leftArraySize = 0;
        rightArraySize = twostack.length;
    }

    public E[] getTwostack() {
        return twostack;
    }

    public boolean isEmpty(Boolean right) {
        return right ? rightArraySize == (twostack.length) : leftArraySize == 0;
    }

    @Override
    public Integer size(Boolean right) {
        return right ? twostack.length - rightArraySize : leftArraySize;
    }

    @Override
    public void push(Boolean right, E element) throws TwostackFullException {
        if (right) {

            if (rightArraySize - leftArraySize > 1) {

                twostack[--rightArraySize] = element;

            } else {

                throw new TwostackFullException();

            }
        } else {

            if (rightArraySize - leftArraySize > 1) {

                twostack[++leftArraySize] = element;

            } else {

                throw new TwostackFullException();

            }
        }
    }

    @Override
    public E pop(Boolean right) throws TwostackEmptyException {
        E topElement;

        if (right) {

            if (rightArraySize < twostack.length) {

                topElement = peek(true);
                twostack[rightArraySize] = null;
                rightArraySize++;

                return topElement;

            } else {

                throw new TwostackEmptyException();

            }

        } else {

            if (leftArraySize > 0) {

                topElement = peek(false);
                twostack[leftArraySize] = null;
                leftArraySize--;

                return topElement;

            } else {

                throw new TwostackEmptyException();

            }
        }
    }

    @Override
    public E peek(Boolean right) throws TwostackEmptyException {

        if (rightArraySize == twostack.length) {

            rightArraySize--;

        }
        if (leftArraySize == 0) {

            leftArraySize++;

        }

        if (right) {

            if (rightArraySize < twostack.length) {

                return twostack[rightArraySize];

            } else {

                throw new TwostackFullException();

            }

        } else {

            if (leftArraySize > 0) {

                return twostack[leftArraySize];

            } else {

                throw new TwostackEmptyException();

            }
        }
    }

    @Override
    public Boolean contains(E element) {

        boolean isFound = false;

        for (E e : twostack) {

            if (element.equals(e)) {

                isFound = true;
                break;

            }
        }

        return isFound;
    }

    @Override
    public void clear() {

        for (int i = 0; i < twostack.length; i++) {

            twostack[i] = null;

        }

        rightArraySize = twostack.length;
        leftArraySize = 0;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
}
