package com.ipn.upiicsa.proy.sstr.userservice.mapper;

import com.ipn.upiicsa.proy.sstr.userservice.dto.StudentDto;
import com.ipn.upiicsa.proy.sstr.userservice.dto.UserDto;
import com.ipn.upiicsa.proy.sstr.userservice.entity.Student;
import com.ipn.upiicsa.proy.sstr.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    default UserDto toDto(User user) {
        if (user instanceof Student)
            return toDto((Student) user);
        else
            return null;
    }

    default User toEntity(UserDto userDto) {

        if (userDto instanceof StudentDto) return toEntity((StudentDto) userDto);
        else return null;
    }


    StudentDto toDto(Student student);

    Student toEntity(StudentDto studentDto);

}
