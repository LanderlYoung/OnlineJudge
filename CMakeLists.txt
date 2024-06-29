cmake_minimum_required(VERSION 3.11)

project(LeetCode LANGUAGES CXX C)
set(CMAKE_CXX_STANDARD 20)
set(LEETCODE_DIR ${CMAKE_CURRENT_LIST_DIR})
set(CMAKE_EXPORT_COMPILE_COMMANDS TRUE)

if (CMAKE_CXX_COMPILER_ID MATCHES "Clang" OR CMAKE_CXX_COMPILER_ID STREQUAL "GNU")
    # macOS doesn't support -fsanitize=memory
    string(APPEND UNIT_TEST_ASAN_FLAGS
            " -fsanitize=address"
            " -fsanitize-recover=address"
            " -fno-omit-frame-pointer")

    # UBSan make UnitTests runs very slow, enable as need
    string(APPEND UNIT_TEST_ASAN_FLAGS
            " -fsanitize=undefined"
            " -fsanitize-recover=undefined")

    string(APPEND CMAKE_C_FLAGS ${UNIT_TEST_ASAN_FLAGS})
    string(APPEND CMAKE_CXX_FLAGS ${UNIT_TEST_ASAN_FLAGS})
    string(APPEND CMAKE_EXE_LINKER ${UNIT_TEST_ASAN_FLAGS})
endif ()

file(GLOB_RECURSE cpp_files *.cc)
foreach(file_path ${cpp_files})
    get_filename_component(dir ${file_path} DIRECTORY)
    get_filename_component(file_name ${file_path} NAME_WE)
    get_filename_component(dir_name ${dir} NAME)

    string(REPLACE "." "" dir_name ${dir_name})
    string(REPLACE " " "_" dir_name ${dir_name})
    string(REPLACE "(" "_" dir_name ${dir_name})
    string(REPLACE ")" "_" dir_name ${dir_name})

    unset(target)
    string(APPEND target ${dir_name} "_" ${file_name})

    message(STATUS "add executable ${target} -> ${file_path}")

   add_executable(${target} ${file_path})
endforeach()

