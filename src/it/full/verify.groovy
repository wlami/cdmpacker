import groovy.json.JsonSlurper;

def parcel_file = new File(basedir, "target/cdmpacker/parcel.json")
def expected = new File(basedir, "expected_parcel.json")
def logFile = new File(basedir, 'build.log')
def slurper = new JsonSlurper()
def expected_parcel = slurper.parseText(expected.text)
def parcel = slurper.parseText(parcel_file.text)

assert new File(basedir, "target/cdmpacker/cdh_env.sh").exists()
assert parcel_file.exists()
assert logFile.exists()
assert expected_parcel == parcel