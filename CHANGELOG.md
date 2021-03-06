# Changelog

## 0.2.2 (released 07.07.2019)

- Update jackson-databind dependency. [#6](https://github.com/wlami/cdmpacker/pull/6)
- Update jackson-databind dependency. [#7](https://github.com/wlami/cdmpacker/pull/7)

## 0.2.1 (released 25.12.2018)

- Update jackson-databind dependency. [#5](https://github.com/wlami/cdmpacker/pull/5)

## 0.2.0 (released 03.10.2018)

- Update vulnerable Maven dependencies ([1], [3]). **This increased the minimum Maven version to 3.5.0.**
However, due to the vulnerabilities ([maven-core@3.3.9], [guava@18.0]), you should update either way.

[1]: https://github.com/wlami/cdmpacker/pull/1
[3]: https://github.com/wlami/cdmpacker/pull/3
[maven-core@3.3.9]: https://snyk.io/vuln/SNYK-JAVA-ORGCODEHAUSPLEXUS-31521
[guava@18.0]: https://snyk.io/vuln/SNYK-JAVA-COMGOOGLEGUAVA-32236

## 0.1.0 (released 02.10.2018)

- Initial release of cdmpacker-maven-plugin with generate-parcel-json mojo.
