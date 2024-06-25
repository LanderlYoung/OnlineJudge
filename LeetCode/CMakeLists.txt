cmake_minimum_required(VERSION 3.11)

project(LeetCode LANGUAGES CXX C)
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_EXPORT_COMPILE_COMMANDS TRUE)

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
