import zipfile
import os
from shutil import copyfile
import argparse

parser = argparse.ArgumentParser(description='information to fill modbon')
parser.add_argument('--docxName', dest='docxName',
                    type=str, help='output file name')

args = parser.parse_args()


def zipdir(path, ziph):
    # ziph is zipfile handle
    for root, _, files in os.walk(path):
        for file in files:
            ziph.write(os.path.join(root, file),
                       os.path.relpath(os.path.join(root, file),
                                       os.path.join(path, '..')))


zipf = zipfile.ZipFile('test.zip', 'w')
try:
    zipdir('customXml/', zipf)
    zipdir('docProps/', zipf)
except:
    pass
zipdir('_rels/', zipf)
zipdir('word/', zipf)
zipf.write('[Content_Types].xml')
zipf.close()

os.rename('test.zip', f'{args.docxName}.docx')
copyfile('original/document.xml', 'word/document.xml')
