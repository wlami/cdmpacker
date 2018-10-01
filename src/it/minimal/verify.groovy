assert new File(basedir, "target/cdmpacker/cdh_env.sh").exists()
assert new File(basedir, "target/cdmpacker/parcel.json").exists()

def logFile = new File(basedir, 'build.log')
assert logFile.exists()


