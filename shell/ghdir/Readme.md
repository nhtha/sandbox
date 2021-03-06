## ghdir

Tiny scripts for getting only specific directories from git repositories that was placed on [GitHub](https://github.com/3F). 

It was designed specially for https://github.com/3F/Examples

Linux & Windows:

```
ghdir {url_to_directory} [{optional_destination_path}]
```

Sample:

```bat
ghdir https://github.com/3F/Examples/tree/master/DllExport/BasicExport
```

```bat
ghdir https://github.com/3F/Examples/tree/master/DllExport/BasicExport my/output/dir
```

Receives only this:

```
└───Examples
    └───DllExport
        └───BasicExport
            ├───ClassLibrary1
            │   └───Properties
            ├───ClassLibrary2
            │   └───My Project
            └───UnmanagedCppConsole
```

## Url support

```
http[s]://github.com/<user>/<project>/tree/<branch>/<path>
```

## How does it work today?

It works either through **svn** client (if you have), or through **git** (that do you probably have).

### Native implementation

It can be also implemented separately from `svn`/`git`, for example, through script for msbuild and so on, but... I just have no plan for this.

#### Why svn is better?

Today's `git` does not provide support of receiving data from specific path at all.

While `svn export` just receives what you need from specific folder, the `git` will also receive all metadata including history (at least one a tree objects if `--depth=1` is used).

Thus, **ghdir** will always try first with svn. Then, if it's failed by some reason, it will use *git clone* with `--depth=1` key  and `core.sparseCheckout` to filter folders.

#### svn for windows (without installation)

just a tip for tortoisesvn:

* https://tortoisesvn.net/downloads.html ([64-bit msi](https://osdn.net/projects/tortoisesvn/storage/1.10.1/Application/TortoiseSVN-1.10.1.28295-x64-svn-1.10.2.msi) / [32-bit msi](https://osdn.net/projects/tortoisesvn/storage/1.10.1/Application/TortoiseSVN-1.10.1.28295-win32-svn-1.10.2.msi))

To extract svn.exe from this msi **without installation**, use **[msiextr](https://github.com/3F/sandbox/tree/master/shell/msiextr)** script:

* https://github.com/3F/sandbox/tree/master/shell/msiextr

Now you can use **ghdir** more efficiently:

* `...\TortoiseSVN\bin\svn.exe`


## Hope

I hope for some future implementing from GitHub like a button to download specific directory. 

And my hope for any ~`svn export` alternative in official git clients.

hmmm, ...

[ [☕](https://3F.github.io/Donation/) ]

::