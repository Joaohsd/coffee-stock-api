run-setup:
	chmod +x scripts/setup/setup.sh
	./scripts/setup/setup.sh

run-unit-test:
	chmod +x scripts/test/unit-test.sh
	./scripts/test/unit-test.sh

run-unit-test:
	chmod +x scripts/test/integration-test.sh
	./scripts/test/integration-test.sh

run-build:
	chmod +x scripts/pack/build.sh
	./scripts/pack/build.sh

run-notify-test:
	chmod +x scripts/notify/notify_test.sh
	./scripts/notify/notify_test.sh

run-notify-build:
	chmod +x scripts/notify/notify_build.sh
	./scripts/notify/notify_build.sh