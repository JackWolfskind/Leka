apache2:
  pkg:
    - installed

  service:
    - running
    - enable: True
    - require:
      - pkg: apache2
      - cmd: auth-ldap-module
    - watch:
      - cmd: apache2

  cmd.run:
    - name: a2dissite 000-default
    - onlyif: stat /etc/apache2/sites-enabled/000-default.conf
    - require:
      - pkg: apache2

  file.absent:
    - names:
      - /var/www/html/index.html
    - require:
      - pkg: apache2


auth-ldap-module:
  cmd.run:
    - name: a2enmod authnz_ldap
    - unless: stat /etc/apache2/mods-enabled/authnz_ldap.load
    - require:
      - pkg: apache2
