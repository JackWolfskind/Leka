
include:
  - sls.dev.php.runtime.websites.apache

extend:
  apache2:
    service:
      - require:
        - pkg: php
      - watch:
        - file: /etc/php5/apache2/conf.d/95-tools.ini
        - file: /etc/php5/apache2/conf.d/95-xdebug.ini

php:
  pkg:
    - installed
    - names:
      - php5
      - libapache2-mod-php5
      - php5-curl
      - php5-memcache
      - php5-memcached
      - php5-mysql
      - php5-ldap
      - php5-sybase
      - php5-xmlrpc
      - php5-xdebug
      - php5-intl
    - require_in:
      - service: apache2
    - watch_in:
      - service: apache2

  file.managed:
    - name: /etc/php5/apache2/conf.d/95-tools.ini
    - source: salt://templates/dev/php/websites/tools/php5/conf.d/95-tools.ini
    - user: root
    - group: root
    - mode: 644

php-xdebug-ini:
  file.managed:
    - name: /etc/php5/apache2/conf.d/95-xdebug.ini
    - source: salt://templates/dev/php/websites/tools/php5/conf.d/95-xdebug.ini
    - user: root
    - group: root
    - mode: 644
    - require:
      - pkg: php

/var/log/phperrors.log:
  file.managed:
    - user: www-data
    - group: adm
    - mode: 644
    - require:
      - file: /etc/php5/apache2/conf.d/95-tools.ini

