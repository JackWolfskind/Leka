mysql-server:
  pkg:
    - installed

  service:
    - running
    - name: mysql
    - enable: True
    - require:
      - pkg: mysql-server

