cmake_minimum_required(VERSION 3.11)

project(LeetCode LANGUAGES CXX C)

set(CMAKE_CXX_STANDARD 17)

set(LEETCODE_DIR ${CMAKE_CURRENT_LIST_DIR})

FILE(GLOB_RECURSE cpp_files *.cc)

set(CMAKE_EXPORT_COMPILE_COMMANDS TRUE)

FOREACH(file_path ${cpp_files})
    GET_FILENAME_COMPONENT(dir ${file_path} DIRECTORY)
    GET_FILENAME_COMPONENT(file_name ${file_path} NAME_WE)
    GET_FILENAME_COMPONENT(dir_name ${dir} NAME)

    string(REPLACE "." "" dir_name ${dir_name})
    string(REPLACE " " "_" dir_name ${dir_name})
    string(REPLACE "(" "_" dir_name ${dir_name})
    string(REPLACE ")" "_" dir_name ${dir_name})

    unset(target)
    string(APPEND target ${dir_name} "_" ${file_name})

    message(STATUS "add executable ${target} -> ${file_path}")

     add_executable(${target} ${file_path})
ENDFOREACH()
