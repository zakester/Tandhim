import argparse
import os

path = 'word/document.xml'

parser = argparse.ArgumentParser(description='information to fill modbon')

parser.add_argument('--requester', dest='@requester',
                    type=str, help='Requester\'s name')

parser.add_argument('--wanted', dest='@wanted',
                    type=str, help='Wanted\'s name')

parser.add_argument('--service', dest='@service', type=str, help='service')

parser.add_argument('--price', dest='@price', type=str, help='price')

parser.add_argument('--rest', dest='@rest', type=str, help='rest')

parser.add_argument('--docxName', dest='docxName', type=str, help='output file name')


args = parser.parse_args()


# Read in the file
with open(path, 'r') as file:
    filedata = file.read()

# Replace text in file
for arg in vars(args):
    value = getattr(args, arg)
    if value is not None:
        filedata = filedata.replace(arg, value)

# Write the file out again
with open(path, 'w') as file:
    file.write(filedata)

output = args.docxName

if output is None:
	output = 'test'

# Convert to docx
#exec(open(f'toDOCX.py').read())
os.system(f'python toDOCX.py --docxName {output}')
