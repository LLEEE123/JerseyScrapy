'''
Created on 2018年5月29日

@author: Lemon
'''

import click
from copy import deepcopy
from pprint import PrettyPrinter

import slice
import STLimport
import GcodeEX


@click.command()
@click.option("--stl", help="Path to input STL file.")
@click.option("--preamble", help="Path to GCode preamble.", default=None)
@click.option("--cleanup", help="Path to GCode cleanup.", default=None)
@click.option("--outpath", help="Path to output location of GCode.")
@click.option("-v", "--verbose", default=False, is_flag=True, help="Toggle to True for verbosity.")
def run(stl, preamble, cleanup, outpath, verbose):
    # Initialize verbose logger.
    p = PrettyPrinter(indent=4)
    pprint = p.pprint

    if verbose:
        print("Loading STL: '{}'\n".format(stl))

    # Parse the STL mesh into a form we can work with.
    parsed, auxdata = STLimport.parse_stl(stl, verbose=verbose)
    if verbose:
        print("Output of parsed STL:")
        pprint(parsed)
        print("\nAuxiliary metadata:")
        pprint(auxdata)
        print("STL mesh ingestion done.\n")

    # Slice the parsed STL mesh
    params = deepcopy(slice.DEFAULT_PARAMETERS)
    sliced = slice.slice_model(parsed, auxdata, params, verbose=verbose)
    if verbose:
        print("Output of sliced mesh:")
        pprint(sliced)
        print("Slicing done.\n")

    # DONE
    if outpath is None:
        outpath = stl.replace(".stl", ".gcode")

    if verbose:
        print("Outputting to: {}".format(outpath))
    GcodeEX.export(sliced, preamble, cleanup, outpath, verbose)

if __name__ == "__main__":
    run()
