// DictData.java
// ��Ԃ�\�����߂̒萔
enum State {
    EMPTY, DELETED, OCCUPIED
};

public class DictData {
    int     name;        // �f�[�^
    State   state;       // ���
    // �R���X�g���N�^
    DictData() {
        state = State.EMPTY;
    }
}


