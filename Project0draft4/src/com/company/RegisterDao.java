package com.company;

import java.util.List;

public interface RegisterDao {
    void addRegister(Register register);

    void updateRegister(Register register);

    void deleteRegister(int registration_id);

    List<Register> getRegister();

    Register getRegisterByID(int registration_id);

}
