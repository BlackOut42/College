// Name: Daniel Nekludov, ID: 321984619
// Name: Gal Rabinovich, ID: 209064500

package DP.Memento;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class DBCaretaker {
    private static Deque<DBMemento> mementoStack;
    private static final int MAX_SAVES = 3;
    private static boolean initialized;
    public DBCaretaker() {
        if (!initialized) {
            mementoStack = new ArrayDeque<>();
            initialized = true;
        }
    }
    public void saveMemento(DBMemento memento) {
        if (mementoStack.size() == MAX_SAVES) {
            mementoStack.removeFirst();
        }
        mementoStack.addLast(memento);
    }
    public DBMemento getMemento(int index) {
        if (index >= 0 && index < mementoStack.size()) {
            return (DBMemento) mementoStack.toArray()[index];
        }
        return null;
    }
    public DBMemento popMemento(int index) {
        if (index >= 0 && index < mementoStack.size()) {
            int currentIndex = 0;
            Iterator<DBMemento> iterator = mementoStack.iterator();
            while (iterator.hasNext()) {
                DBMemento memento = iterator.next();
                if (currentIndex == index) {
                    iterator.remove();
                    return memento;
                }
                currentIndex++;
            }
        }
        return null;
    }
}
