cd "$(dirname -- "${BASH_SOURCE[0]}")"

mkdir -p cmake-build-debug

pushd cmake-build-debug
cmake -DCMAKE_BUILD_TYPE=Debug ..
popd

if [[ ! -f "compile_commands.json" ]]; then
    ln -s cmake-build-debug/compile_commands.json ./
fi

