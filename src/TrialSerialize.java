import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class TrialSerialize<A> {
    String filename;

    public TrialSerialize(String filename) {
        this.filename = filename;
    }

    public void writeTrialList(TrialList<A> trialList) {
        A[] aArray = trialList.toArray();
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeInt(aArray.length);
            for (A a : aArray) {
                output.writeObject(a);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public TrialList<A> readTrialList() {
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            TrialList<A> list = new TrialList<>();
            int count = input.readInt();
            for (int i = 0; i < count; i++) {
                list.add((A)input.readObject());
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
